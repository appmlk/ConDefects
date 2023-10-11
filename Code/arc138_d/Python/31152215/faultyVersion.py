n,k = map(int,input().split())
if k == n or k%2 == 0:
    print("No")
    exit()


graycode = [i^(i>>1) for i in range(1<<n)]

basis = []
cand = []
for i in range(1<<n):
    if bin(i).count("1") != k:
        continue
    now = i
    for b in basis:
        now = min(now,now^b)
    if now:
        basis.append(now)
        cand.append(i)


ans = []
for g in graycode:
    num = 0
    for i in range(n):
        if g >> i & 1:
            num ^= cand[i]
    ans.append(num)

print("Yes")
print(*ans)