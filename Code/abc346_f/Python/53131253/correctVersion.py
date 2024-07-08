n = int(input())
s = input()
t = input()
# 定义p(a,b,c)为f(s,n)的下标a后的出现的第b个字符c的位置
# 1.如果a>len(s),可以等价为p(a-len(s),b,c)+len(s)
# 2.如果s.count(c)<b,可以等价为p(a+len(s),b-s.count(c),c)
# 以上两步可以将a,b转为0<=a<len(s), 0<=b<s.count(c)
cnt = [0]*26
pos = [[] for _ in range(26)]
pre = [[0]*26 for _ in range(len(s)+1)]
for i, c in enumerate(s*2):
    idx = ord(c)-ord('a')
    pos[idx].append(i)
for i in range(26):
    cnt[i] = len(pos[i])//2
for i in range(1, len(s)+1):
    pre[i] = pre[i-1][:]
    pre[i][ord(s[i-1])-ord('a')] += 1
def check(m):
    it = 0
    for i in range(len(t)):
        d = ord(t[i])-ord('a')
        if cnt[d] == 0:
            return False
        r = (m-1) % cnt[d] + 1
        b = (m-r) // cnt[d]
        it += len(s)*b
        nx = pos[d][pre[it%len(s)][d]+r-1]
        it += nx+1-it%len(s)
        if it > len(s)*n:
            return False
    return True
ans = 0
l, r = 1, 10**18
while l <= r:
    mid = (l+r)>>1
    if check(mid):
        ans = mid
        l = mid+1
    else:
        r = mid-1
print(ans)