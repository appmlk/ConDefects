N = int(input())
CS = [0]*(2*10**5 + 1)
LR = [list(map(int, input().split())) for _ in range(N)]
LR += [[2*10**5,2*10**5]] # 番兵
LR.sort()

left,right,ans = 0,0,[]
for i in range(N+1):
    L,R = LR[i][0],LR[i][1]
    if right < L: # 新しい区間
        ans.append((left,right))
        left,right = L,R
    elif right < R: # 区間を延長
        right = R

for l,r in ans[1:]:
    print(l,r)
