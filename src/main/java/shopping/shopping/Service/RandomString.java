package shopping.shopping.Service;

import java.util.Random;

public class RandomString {

    private StringBuffer m;
    private int wordCount ;

    /**
     * 기본 문자열 수는 20입니다.
     *
     * word-Count를 통해 원하는 랜덤 글자수를 얻을 수 있습니다.
     *
     * */
    public RandomString(int wordCount){
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < wordCount ; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        m = temp;
    }
    public RandomString(){
        this(20);
    }
    public StringBuffer getRandomString(){
        return m;
    }
    public void setWordCount(int wordCount){
        this.wordCount = wordCount;
    }
}
