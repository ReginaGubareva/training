public class Numbers {
    public static void main(String[] args) {
        int count = 0;
        for(int i = 100; i <= 999; i++){
            if(i%10 == 8 && (i/10)%10!=8 && (i/100)%10!=8) {
                count ++;
            }
            if(i%10 != 8 && (i/10)%10==8 && (i/100)%10!=8) {
                count ++;
            }
            if(i%10 != 8 && (i/10)%10!=8 && (i/100)%10==8) {
                count ++;
            }
        }
        System.out.print(count);
    }
}
