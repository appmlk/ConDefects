import sys
input = sys.stdin.readline

from math import ceil, sqrt
class Mo():
    def __init__(self, A):
        self.M = 2020202
        self.M2 = self.M**2
        self.A = A
        self.N = len(A)
        self.B = ceil(sqrt(self.N))
        self.K = self.N // self.B + 1
        self.Query = [[] for i in range(self.K)]
        self.nowL = 0
        self.nowR = 0
        self.ans = None

        #------------------------------------------
        self.data = [0] * self.M


    def encode(self, r, l, ind):
        return ind + l * self.M + r * self.M2

    def decode(self, v):
        ind = v % self.M
        v //= self.M
        r, l = divmod(v, self.M)
        return r, l, ind

    def add(self, i):
        val = self.data[self.A[i]]
        self.data[self.A[i]] ^= 1
        return val        

    def delete(self, i):
        val = self.data[self.A[i]]
        self.data[self.A[i]] ^= 1
        return 1 - val        

    def solve(self, query):
        Q = len(query)
        self.ans = [0] * Q
        for i in range(Q):
            l, r = query[i]
            l -= 1
            self.Query[l//self.B].append(self.encode(r, l, i))

        for i in range(self.K):
            self.Query[i].sort()

        val = 0
        for i in range(self.K):
            for v in self.Query[i]:
                r, l, ind = self.decode(v)
                while self.nowR < r:
                    val += self.add(self.nowR)
                    self.nowR += 1
                while self.nowR > r:
                    self.nowR -= 1
                    val -= self.delete(self.nowR)
                while self.nowL < l:
                    val -= self.delete(self.nowL)
                    self.nowL += 1
                while self.nowL > l:
                    self.nowL -= 1
                    val += self.add(self.nowL)

                self.ans[ind] = val

        return self.ans
    
N = int(input())
A = list(map(int, input().split()))
Q = int(input())
query = []
for _ in range(Q):
    query.append(list(map(int, input().split())))

mo = Mo(A)
for a in mo.solve(query):
    print(a)