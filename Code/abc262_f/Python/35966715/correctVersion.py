import sys
input = lambda: sys.stdin.readline().rstrip()
ii = lambda: int(input())
mi = lambda: map(int, input().split())
li = lambda: list(mi())
inf = 2 ** 63 - 1
mod = 998244353

class SWAG():
    def __init__(self, op, e):
        self.op = op
        self.e = e
        self.top = []
        self.bottom = []
        self.topfold = [e]
        self.bottomfold = [e]
    def _pushbottom(self, x):
        self.bottom.append(x)
        self.bottomfold.append(self.op(self.bottomfold[-1], x))
    def _popbottom(self):
        self.bottomfold.pop()
        return self.bottom.pop()
    def _pushtop(self, x):
        self.top.append(x)
        self.topfold.append(self.op(x, self.topfold[-1]))
    def _poptop(self):
        self.topfold.pop()
        return self.top.pop()
    def push(self, x):
        self._pushbottom(x)
    def fold(self):
        return self.op(self.topfold[-1], self.bottomfold[-1])
    def pop(self):
        if not self.top:
            while self.bottom:
                x = self._popbottom()
                self._pushtop(x)
        if not self.top:
            return self.e
        else:
            return self._poptop()

def solve(a, k):
    n = len(a)
    S = SWAG(min, inf)
    for i in range(k):
        S.push(a[i])
    ans = []
    for i in range(k, n):
        S.push(a[i])
        ans.append(S.fold())
        while True:
                x = S.pop()
                if x == ans[-1]:
                    break

    return ans

def solve2(a, x):
    n= len(a)
    S = SWAG(min, inf)
    for i in range(n):
        S.push(a[i])
    ans = []
    for i in range(n):
        if len(S.top) + len(S.bottom) == 0:
            break
        now = S.fold()
        if now < x:
            ans.append(now)
            while True:
                to = S.pop()
                if to == ans[-1]:
                    break
        else:
            break
    return ans
        

n, k = mi()

p = li()

if k == 0:
    print(*p)
    exit()
ans1 = solve(p, k)
mini = min(p[n - k:])
x = p.index(mini)
p2 = p[x:]
p3 = p[:x]
ansy = solve(p3, k - (n - x))
ansx = solve2(p2, ansy[0])
ans2 = ansx + ansy
print(*min(ans1, ans2))

