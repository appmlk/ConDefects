from itertools import combinations

def solve():
    N = int(input())
    xs = list(map(int, input().split()))
    a = sorted(v for v in xs if v > 0)
    b = sorted(v for v in xs if v < 0)
    c = a[:3] + a[3:][-3:] + b[:3] + b[3:][-3:]
    mx, mi = -float('inf'), float('inf')
    for x, y, z in combinations(c, 3):
        t = (x+y+z)/(x*y*z)
        mi = min(mi, t)
        mx = max(mx, t)
      
    print(mi)
    print(mx)
  
solve()