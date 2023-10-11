import sys
readline = sys.stdin.readline

def gcd(a,b):
    while b:
        a, b = b, a%b
    return abs(a)
def lcm(a, b):
    return a*b//gcd(a, b)

class UFP():
    def __init__(self, num, N):
        self.par = [-1]*num
        self.dist = [0]*num
        self.gg = [N]*num
    def find(self, x):
        if self.par[x] < 0:
            return x
        else:
            res = 0
            xo = x
            while self.par[x] >= 0:
                res += self.dist[x]
                x = self.par[x]
            self.dist[xo] = res
            self.par[xo] = x
            return x
    
    def union(self, x, y, d):
        rx = self.find(x)
        ry = self.find(y)
        if rx != ry:
            if self.par[rx] > self.par[ry]:
                rx, ry = ry, rx
                x, y = y, x
                d = -d
            self.par[rx] += self.par[ry]
            self.par[ry] = rx
            self.dist[ry] = d + self.dist[x] - self.dist[y]
            
            
            pre = self.gg[rx] + self.gg[ry]
            self.gg[rx] = gcd(self.gg[rx], self.gg[ry])
            return self.gg[rx] - pre
        else:
            pre = self.gg[rx]
            self.gg[rx] = gcd(self.gg[rx], d + (self.dist[x] - self.dist[y]))
                
            return self.gg[rx] - pre

def calc(a, b):
    if b > a:
        b -= N
    X, Y = a, a-b
    assert (a-b) >= 0
    return X, Y

N, Q = map(int, readline().split())
ans = N*N

Ans = [ans]
T = UFP(N, N)
for _ in range(Q):
    a, b, c, d = map(int, readline().split())
    X, Y = calc(a, b)
    Z, W = calc(c, d)
    Ans.append(Ans[-1] + T.union(Y, W, X-Z))

print('\n'.join(map(str, Ans[1:])))
    
    
    
