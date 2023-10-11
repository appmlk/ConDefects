import os
import sys
from io import BytesIO, IOBase
import bisect

BUFSIZE = 4096
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

sys.stdin = IOWrapper(sys.stdin)
sys.stdout = IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")

def I():
    return input()

def SI():
    return str(input())

def II():
    return int(input())

def LII():
    return list(map(int, input().split()))


n = II()
a = LII()
neg, pos = [], []
for i in range(n):
    x = a[i]
    if x > 0:
        pos.append(x)
    else:
        neg.append(x)
neg.sort()
pos.sort()
arr = []
m1 = min(len(neg), 3)
for i in range(m1):
    arr.append(neg[i])
m1 = max(m1, len(neg) - 3)
for i in range(m1, len(neg)):
    arr.append(a[i])
m2 = min(len(pos), 3)
for i in range(m2):
    arr.append(pos[i])
m2 = max(m2, len(pos) - 3)
for i in range(m2, len(pos)):
    arr.append(pos[i])
mn, mx = (arr[0] + arr[1] + arr[2]) / (arr[0] * arr[1] * arr[2]), (arr[0] + arr[1] + arr[2]) / (arr[0] * arr[1] * arr[2])
for i, x in enumerate(arr):
    for j in range(i + 1, len(arr)):
        y = arr[j]
        for k in range(j + 1, len(arr)):
            z = arr[k]
            res  = (x + y + z) / (x * y * z)
            mn = min(mn, res)
            mx = max(mx, res)
print("%.15f" % mn)
print("%.15f" % mx)

