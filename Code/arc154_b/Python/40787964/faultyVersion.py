n = int(input())
s = input()
t = input()

s2 = sorted(s)
t2 = sorted(s)
if s2 != t2:
    print(-1)
    exit()

if s == t:
    print(0)
    exit()

def check(x):
    s_cut = s[x:]
    l = len(s_cut)
    s_idx = 0
    for i in range(n):
        if t[i] == s_cut[s_idx]:
            s_idx += 1
        
        if s_idx == l:
            return True
    
    return False

ok = n
ng = 0

while ok-ng >= 2:
    mid = (ok+ng)//2
    if check(mid):
        ok = mid
    else:
        ng = mid

print(ok)