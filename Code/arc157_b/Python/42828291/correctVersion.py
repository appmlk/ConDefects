import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
# b = [list(map(int,readline().split())) for _ in range()]

n,k = map(int,readline().split())
s = readline().strip()

y = s.count("Y")
x = n-y

if x <= k:
    # x を全部 y に変える
    # y のうち k 個を x に変える
    k -= x
    s = ["X" if i == "Y" else "Y" for i in s]
    s = "".join(s)
    k = y-k

#print(s,k)

# x が k 個以上
# x のうち k 個を y に変える

ans = 0
for i in range(1,n):
    if s[i-1] == s[i] == "Y": ans += 1


*r, = map(len,s.split("Y"))
r = r[1:-1]


if s.count("Y") == 0:
    print(max(0,k-1))
    exit()

#print(s,k,ans,r)
r.sort(reverse=1)
while r:
    v = r.pop()
    if v==0: continue
    if v <= k:
        k -= v
        ans += v+1
    else:
        ans += k
        k = 0
    
if k: ans += k
print(ans)
    
    
    







