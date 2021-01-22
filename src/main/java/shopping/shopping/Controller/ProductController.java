package shopping.shopping.Controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shopping.shopping.DTO.ProductDTO;
import shopping.shopping.Entity.Member;
import shopping.shopping.Entity.Product;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Repository.ProductRepository;
import shopping.shopping.Service.FileSave;
import shopping.shopping.Service.MemberService;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class ProductController {

    @PersistenceContext
    private final EntityManager em;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;

    public ProductController(EntityManager em, MemberRepository memberRepository, MemberService memberService, ProductRepository productRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.productRepository = productRepository;
    }

    @GetMapping("/test")
    public byte[] testImage(
            HttpServletResponse response
    ){

        Image image = new ImageIcon("E:\\download\\basicuser.png").getImage();

        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(image,0,0,null);
        g2d.dispose();

        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            ImageIO.write(bi,"png",baos);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                baos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = Base64.encodeBase64(bais.readAllBytes());

        return bytes;
    }

    @PostMapping("/test/pdf")
    public byte[] testPdf(
            HttpServletResponse response
    ) throws IOException {
        File file = new File("C:\\Users\\bdgom\\OneDrive\\desktop\\Gool.pdf");

        ByteArrayInputStream bais;
        byte[] toBy = new byte[(int) file.length()];
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            toBy  = Base64.encodeBase64(fis.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBy;
    }
    @GetMapping("/product/all")
    public List<ProductDTO> productAll(
            HttpServletResponse response
    ) throws IOException {
        try{
            List<Product> all = productRepository.findAll();
            List<ProductDTO> reProduct = new ArrayList<>();
            for (Product product : all) {
                reProduct.add(new ProductDTO(product.getId(),product.getName(),product.getExplain(),product.getPhoto(),product.getKind(),
                        product.getPrice(),product.getQuantity(),product.getMember()));
            }
            return reProduct;

        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"알수 없는 오류");
            return null;
        }
    }

    // 이미지 파일 x
    @PostMapping("/product/create")
    public Long productCreate(
            @RequestParam(value= "kind") String kind,
            @RequestParam(value = "photo",required = false)  MultipartFile file,
            @RequestParam("product") String name,
            @RequestParam(value="explain",required = false) String explain,
            @RequestParam("price") String price,
            @RequestParam("quantity") String quantity,
            @RequestHeader(value = "cookies") String cookie,
            HttpServletResponse response
    ) throws IOException {
            Long userID = memberService.parserCookie("1");
            Optional<Member> byId = memberRepository.findById(userID);
            if(byId.isEmpty()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"작성자가 존재하지 않음.");
            }
            try{
                FileSave fs = new FileSave(file);
                System.out.println("\"classpath:/\" = " + ClassPath.getClassPath());
                Product _product = new Product(name,explain,fs.getImageAddress());
                _product.setPrice(Long.parseLong(price));
                _product.setQuantity(Long.parseLong(quantity));
                _product.setMember(byId.get());
                byId.get().getProducts().add(_product);
                Product save = productRepository.save(_product);
                return save.getId();
            }catch (Exception e){
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
                return null;
            }
    }


    @PostMapping("/product/{id}/detail")
    public ProductDTO productDetail(
            @RequestParam("id") String id,
            HttpServletResponse response
    ) throws IOException {
        Long product_id = Long.parseLong(id);
        try{
            Optional<Product> findProduct = productRepository.findById(product_id);
            if(findProduct.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않는 상품입니다.");
            }
            Product product = findProduct.get();
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),product.getName(),product.getExplain(),product.getPhoto(),
                    product.getKind(),product.getPrice(),product.getQuantity(),product.getMember());
            response.setStatus(HttpServletResponse.SC_OK);
            return productDTO;
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
            return null;
        }

    }
    @PostMapping("/product/{id}/update")
    public void productUpdate(
            @RequestParam("id") String id,
            @RequestParam(value = "photo",required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("explain") String explain,
            @RequestParam("price") String price,
            @RequestParam("quantity") String quantity,
            @RequestHeader(value = "cookies") String cookie,
            HttpServletResponse response
    ) throws IOException {
        Long memberId = memberService.parserCookie(cookie);
        Long priceLong = Long.parseLong(price);
        Long quantityLong = Long.parseLong(quantity);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Optional<Product> findProduct = productRepository.findById(Long.parseLong(id));
        if(findMember.isEmpty() || findProduct.isEmpty()){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"존재하지않음");
        }
        if(!findMember.get().getId().equals(findProduct.get().getMember().getId())){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"작성자가 아님");
        }
        findProduct.get().setName(name);
        findProduct.get().setExplain(explain);
        findProduct.get().setPrice(priceLong);
        findProduct.get().setQuantity(quantityLong);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    @PostMapping("/product/{id}/delete")
    public void productDelete(
            @RequestParam("id") String id,
            @RequestHeader(value = "cookies") String cookie,
            HttpServletResponse response
    ) throws IOException {
        Long memberID = memberService.parserCookie(cookie);
        Long productID = Long.parseLong(id);
        Optional<Member> findMember = memberRepository.findById(memberID);
        Optional<Product> findProduct = productRepository.findById(productID);
        if(findMember.isPresent() && findProduct.isPresent()){
            if(findMember.get().getId().equals(findProduct.get().getMember().getId())){
                productRepository.delete(findProduct.get());
                findMember.get().getProducts().remove(findProduct);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }else{
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"작성자가 다름");
            }
        }else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"존재하지 않음");
        }
    }
}
