import java.util.*;
class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    Map<Integer, List<Integer>> slotNumSt = new HashMap<>();
    int ansTime = 999999;
    // 0-9の目ごとに何秒で止まるかリストで
    for(int i = 0; i < 10; i++) slotNumSt.put(i, new ArrayList<Integer>());
    for(int j = 0; j < num; j++) {
      String[] s = sc.next().split("");
      for(int k = 0; k < 10; k++) {
        slotNumSt.get(Integer.parseInt(s[k])).add(k);
      }
    }
    for(int slNum = 0; slNum < 10; slNum++) {
      List<Integer> numList = slotNumSt.get(slNum);
      Collections.sort(numList);
      // 0-9までの目で最大何秒かかるかみたい
      int maxT = 0;
      //ループの回数と最終ループでは何秒で止まるか
      int sLoop = 0;
      int stopNum = 0;
      // temp 数字毎のスロットループ回数確認
      int tempCount = 0;
      int tempNum = -1;
      for(int sIndexCheck = 0; sIndexCheck < num; sIndexCheck++) {
        if(tempNum != numList.get(sIndexCheck)) {
          tempNum = numList.get(sIndexCheck);
          tempCount = 0;
          continue;
        }
        tempCount++;
        if(tempCount >= sLoop) {
          sLoop = tempCount;
          stopNum = tempNum;
        }
      }
      
      if(tempCount >= sLoop) stopNum = tempNum;
      maxT = sLoop * 10 + stopNum;
      if(ansTime > maxT) ansTime = maxT;
    }
    System.out.print(ansTime);
    sc.close();

  }
}