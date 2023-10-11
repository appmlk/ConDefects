#!/usr/bin/env python
import os
import sys
from io import BytesIO, IOBase


def main():
    n, k = map(int,input().split())
    if n == 1 and k == 1:
        print("Yes")
        print("0")
        return
    if k >= n:
        print("No")
        return
    if k % 2 == 0:
        print("No")
    else:
        if k == 1:
            ans = []
            for i in range(1 << n):
                ans.append(i ^ (i >> 1))
            print("Yes")
            print(" ".join(map(str, ans)))
            return
        curN = k + 1
        curAns = []
        MAX = (1 << curN) - 1
        for i in range(1 << curN):
            if i & 1:
                curAns.append(MAX ^ i ^ (i >> 1))
            else:
                curAns.append(i ^ (i >> 1))
        
        while curN < n:
            flag = False
            for t in range(1 << curN):
                for i in range(1 << curN):
                    iCpy = curAns[t] ^ curAns[i]
                    cnt = 0
                    while iCpy:
                        cnt += (iCpy & 1)
                        iCpy >>= 1
                    iCpy = curAns[t-1] ^ curAns[i - 1]
                    cnt1 = 0
                    while iCpy:
                        cnt1 += (iCpy & 1)
                        iCpy >>= 1
                    if cnt == k - 1 and cnt1 == k - 1:
                        flag = True
                        break
                if flag == True:
                    if t == 0:
                        nextAns = curAns[::-1] + curAns[i:] + curAns[:i]
                        for i in range(1 << curN):
                            nextAns[i] ^= (1 << curN)
                    else:
                        nextAns = curAns[:t - 1:-1] + curAns[i:] + curAns[:i] + curAns[t - 1::-1]
                        for i in range((1 << curN) - t, (1 << curN) * 2 - t):
                            nextAns[i] ^= (1 << curN)
                    break
            curN += 1
            curAns = nextAns
        print("Yes")
        print(" ".join(map(str, curAns)))
        for i in range(len(curAns)):
            iCpy = curAns[i] ^ curAns[(i + 1) % len(curAns)]
            cnt = 0
            while iCpy:
                cnt += (iCpy & 1)
                iCpy >>= 1
            if cnt != k:
                print("NOWOER")
        curAns.sort()
        for i in range(len(curAns)):
            if curAns[i] != i:
                print("NOOOO")



# region fastio

BUFSIZE = 8192


class FastIO(IOBase):
    newlines = 0

    def __init__(self, file):
        self._file = file
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

# endregion

if __name__ == "__main__":
    main()