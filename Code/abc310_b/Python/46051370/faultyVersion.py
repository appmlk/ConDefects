def main():
    # input
    # N:商品数 M:最大機能値
    N, M = map(int,input().split())

    # init
    PCF = [0 for _ in range(N)]
    # P:価格 C:機能数 F:機能
    for i in range(N):
        # 0-N個
        PCF[i] = list(map(int,input().split()))
    # print(PCF)
    
    ans = False
    # 総当たりで確認
    for i in range(N):
        # C
        P_i = PCF[i][0]
        # F
        F_i = PCF[i][2:PCF[i][1]+2]
        for j in range(N):#i+1
            # C
            P_j = PCF[j][0]
            # F
            F_j = PCF[j][2:PCF[j][1]+2]
            # すべて満たす条件
            if (P_i > P_j) and (set(F_j) >= set(F_i)) and ((P_i > P_j) or (set(F_j) > set(F_i))):
                ans = True # True == 1
                # print(i,j,F_i,F_j)

    # output
    if ans:
        print("Yes")
    else:
        print("No")

main()