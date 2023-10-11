import sys
import os
from io import BytesIO, IOBase
BUFSIZE = 8192
class FastIO(IOBase):
    newlines = 0
    def __init__(self, file):
        self._fd = file.fileno()
        self.buffer = BytesIO()
        self.writable = "x" in file.mode or "r" not in file.mode
        self.write = self.buffer.write if self.writable else None
    def read(self):
        while True:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            if not b:
                break
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines = 0
        return self.buffer.read()
    def readline(self):
        while self.newlines == 0:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            self.newlines = b.count(b"\n") + (not b)
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines -= 1
        return self.buffer.readline()
    def flush(self):
        if self.writable:
            os.write(self._fd, self.buffer.getvalue())
            self.buffer.truncate(0), self.buffer.seek(0)
class IOWrapper(IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")
sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")

def I():
    return input()
def II():
    return int(input())
def MI():
    return map(int, input().split())
def LI():
    return list(input().split())
def LII():
    return list(map(int, input().split()))
def GMI():
    return map(lambda x: int(x) - 1, input().split())

#------------------------------FastIO---------------------------------

from bisect import *
from heapq import *
from collections import *
from functools import *
from itertools import *
#dfs - stack#
#check top!#

def solve():
    n = II()
    A = LII()
    B = LII()
    if A[0] != B[0]:
        print('No')
        return

    ans = []
    for i in range(n - 1, -1, -1):
        #print('A', i, A)
        #print('B', i, B)
        if A[i] == B[i]:
            continue
        else:
            bases = []
            ind = []
            for j in range(i):
                b = B[j]
                for x in bases:
                    b = min(b, b ^ x)
                if b != 0:
                    bases.append(b)
                    ind.append(j)
            #print('bases', bases)
        
            x = A[i] ^ B[i]
            for b in bases:
                x = min(x, x ^ b)
            if x:
                print('No')
                return
            
            prod = 0
            for b in B[:i]:
                prod ^= b
            prod ^= A[i] ^ B[i]
            sz = len(bases)
            j = sz - 1
            while j >= 0 and prod > 0:
                x = prod
                for k in range(j + 1):
                    x = min(x, x ^ bases[k])
                    if x == 0:
                        ind_ = k
                        break
                
                ans.append(ind[ind_] + 1)
                p = 0
                nbases = []
                for k in range(ind[ind_] + 1):
                    B[k + 1] ^= B[k]
                    prod ^= B[k]
                    if k == ind[p]:
                        b = B[k]
                        for x in nbases:
                            b = min(b, b ^ x)
                        nbases.append(b)
                        p += 1
                nbases.pop()
                bases = nbases
                j = ind_ - 1
            ans.append(i)
            for j in range(i):
                B[j + 1] ^= B[j]
    
    print('Yes')
    print(len(ans))
    print(*[a + 1 for a in ans[::-1]])

for _ in range(1):solve()