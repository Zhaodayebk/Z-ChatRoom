package client;


@SuppressWarnings({"all"})
public class RandomStrUtil {
    //生成随机字符串
    public static String getRandomString(int w){

        Integer it;

        String rand = "";

        for (int i = 0; i < w; i++) {
            it = (int)(Math.random()*(26 - 1 + 1));
            if (it == 1){
                rand = rand + "a";
            }
            if (it == 2){
                rand = rand + "b";
            }
            if (it == 3){
                rand = rand + "c";
            }
            if (it == 4){
                rand = rand + "d";
            }
            if (it == 5){
                rand = rand + "e";
            }
            if (it == 6){
                rand = rand + "f";
            }
            if (it == 7){
                rand = rand + "g";
            }
            if (it == 8){
                rand = rand + "h";
            }
            if (it == 9){
                rand = rand + "i";
            }
            if (it == 10){
                rand = rand + "j";
            }
            if (it == 11){
                rand = rand + "k";
            }
            if (it == 12){
                rand = rand + "l";
            }
            if (it == 13){
                rand = rand + "m";
            }
            if (it == 14){
                rand = rand + "n";
            }
            if (it == 15){
                rand = rand + "o";
            }
            if (it == 16){
                rand = rand + "p";
            }
            if (it == 17) {
                rand = rand + "q";
            }
            if (it == 18) {
                rand = rand + "r";
            }
            if (it == 19) {
                rand = rand + "s";
            }
            if (it == 20) {
                rand = rand + "t";
            }
            if (it == 21) {
                rand = rand + "u";
            }
            if (it == 22) {
                rand = rand + "v";
            }
            if (it == 23) {
                rand = rand + "w";
            }
            if (it == 24) {
                rand = rand + "x";
            }
            if (it == 25) {
                rand = rand + "y";
            }
            if (it == 26) {
                rand = rand + "z";
            }

        }
        return rand.toUpperCase();
    }
}
