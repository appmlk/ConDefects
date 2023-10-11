n = int(input())
prl = [0] *(10**6)
def pr_jg(p):
    pr = int(p ** (1/2)) + 1
    m = 2
    while m <= pr:
        if p % m == 0:
            return(False)
        m += 1
    return True

k = int((n//2) ** (1/3))
prl[0] = 2
cur = 1
for i in range(3,k+2):
    if pr_jg(i):
        prl[cur] = i
        cur += 1
maxv = cur-1
ans = 0
for i in range(cur):
    for j in range(maxv,i,-1):
        if prl[i] * (prl[j]**3) <= n:
            maxv = j
            ans += j - i
            break
 
print(ans)