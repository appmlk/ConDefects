N = int(input())

def calc(m, n):
    return (m**2+n**2)*(m+n)

cand = set()
for a in range(10**6+1):
    l,r = 0, 10**6+10
    while r-l>1:
        m = (l+r)//2

        if calc(a,m)>=N:
            r = m
        else:
            l = m
    
    cand.add(calc(a,r))
print(min(cand))