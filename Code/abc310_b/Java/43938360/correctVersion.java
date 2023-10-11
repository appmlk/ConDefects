import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {

        sc.nextInt();
        int specTypeNum = sc.nextInt();

        // 商品情報リスト生成
        List<Product> productList = new ArrayList<>();

        // 一つ目の商品をリストに格納
        int price = sc.nextInt();
        int numOfSpec = sc.nextInt();
        int[] specArray = new int[specTypeNum]; // int の配列は 0 埋めして生成される
        for(int i = 0; i < numOfSpec; i++){
            specArray[sc.nextInt() - 1] = 1;
        }
        Product product = new Product(price, numOfSpec, specArray);
        productList.add(product);

        // 二つ目以降の情報取得・リスト内データとの比較
        while(sc.hasNext()){

            // 情報取得
            price = sc.nextInt();
            numOfSpec = sc.nextInt();
            specArray = new int[specTypeNum];
            for(int i = 0; i < numOfSpec; i++){
                specArray[sc.nextInt() - 1] = 1;
            }
            Product product_ = new Product(price, numOfSpec, specArray);

            // リスト内データとの比較
            boolean hasPlusOne;
            boolean hasMinusOne;
            for(Product p : productList){

                hasPlusOne = false;
                hasMinusOne = false;
                for(int i = 0; i < specTypeNum; i++){
                    if(product_.getSpecArray()[i] - p.getSpecArray()[i] == 1){
                        hasPlusOne = true;
                    }
                    if(product_.getSpecArray()[i] - p.getSpecArray()[i] == -1){
                        hasMinusOne = true;
                    }
                }

                // +1 も -1 もあれば互換性なし
                if (hasPlusOne && hasMinusOne){
                    continue;
                }

                // product_ の方が機能が多く、値段も同じかより安い
                if(hasPlusOne && product_.getPrice() <= p.getPrice()){
                    System.out.println("Yes");
                    return;
                }
                
                // p の方が機能が多く、値段も同じかより安い
                if(hasMinusOne && p.getPrice() <= product_.getPrice()){
                    System.out.println("Yes");
                    return;
                }

                // 機能が同じで、どちらかの値段がより安い (= 値段が異なる)
                if(!hasPlusOne && !hasMinusOne && product_.getPrice() != p.getPrice()){
                    System.out.println("Yes");
                    return;
                }
            }
            // リスト中のどの商品と比較しても条件が合わない場合、リストに追加
            productList.add(product_);
        }
        sc.close();

        // 全商品が条件に合わない
        System.out.println("No");
    }

    static class Product {
        private int price;
        private int numOfSpec;
        private int[] specArray;

        public Product(int price, int numOfSpec, int[] specArray){
            this.price = price;
            this.numOfSpec = numOfSpec;
            this.specArray = specArray;
        }

        public int getPrice(){
            return this.price;
        }

        public int getNumOfSpec(){
            return this.numOfSpec;
        }

        public int[] getSpecArray(){
            return this.specArray;
        }
    }
}