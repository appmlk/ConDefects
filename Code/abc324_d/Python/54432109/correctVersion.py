from collections import defaultdict

N = int(input())
dic = defaultdict(int)
S = input()
S_cnt = [0]*10
for i in S:
    S_cnt[int(i)] += 1

ans = 0
for i in range(int(10**6.5) + 1):
    tmp = list(str(i**2))
    
    cnt = [0]*10
    for j in tmp:
        cnt[int(j)] += 1
    flag = True
    if S_cnt[0] < cnt[0]:
        flag = False
    else:
        for j in range(1,10):
            if cnt[j] != S_cnt[j]:
                flag = False
                break

    if flag:
        ans += 1
print(ans)