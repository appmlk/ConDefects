import os,sys,math,random,threading
from copy import deepcopy
from io import BytesIO, IOBase
from types import GeneratorType
from functools import lru_cache, reduce
from bisect import bisect_left, bisect_right
from collections import Counter, defaultdict, deque
from itertools import accumulate, combinations, permutations
from heapq import nsmallest, nlargest, heapify, heappop, heappush
from typing import Generic, Iterable, Iterator, TypeVar, Union, List
import types
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
mod = int(1e9 + 7) #998244353
inf = int(1e20)
input = lambda: sys.stdin.readline().rstrip("\r\n")
MI = lambda :map(int,input().split())
li = lambda :list(MI())
ii = lambda :int(input())
py = lambda :print("YES")
pn = lambda :print("NO")
ascii_lowercase='abcdefghijklmnopqrstuvwxyz'
DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]  # 右下左上
DIRS8 = [(0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0),(-1, 1)]  # →↘↓↙←↖↑↗

class StringHash:
    # 字符串哈希，用O(n)时间预处理，用O(1)时间获取段的哈希值
    def __init__(self, s):
        n = len(s)
        self.BASE = BASE = 131313  # 进制 31,131,131313
        self.MOD = MOD = 10 ** 13 + 7  # 10**13+37 ,10**13+51 ,10**13+99 ,10**13+129 ,10**13+183
        self.h = h = [0] * (n + 1)
        self.p = p = [1] * (n + 1)
        for i in range(1, n + 1):
            p[i] = (p[i - 1] * BASE) % MOD
            h[i] = (h[i - 1] * BASE + ord(s[i - 1])) % MOD

    # 用O(1)时间获取闭区间[l,r]（即s[l:r]）的哈希值，比切片要快
    def get_hash(self, l, r):
        return (self.h[r+1] - self.h[l] * self.p[r - l + 1]) % self.MOD
    
    # 获取 s[l1:r1+1] 和 s[l2:r2+1] 拼接的哈希值，要求不能有重叠部分，且有先后顺序
    def get_addhash(self, l1, r1, l2, r2):
        return (self.get_hash(l1,r1)*self.p[r2-l2+1]+self.get_hash(l2,r2))%self.MOD



from collections import defaultdict

class SuffixArray:
    def sa_naive(self, s):       #实现了最朴素的后缀数组构建算法，其时间复杂度为 O(n^2 log n)，适用于小规模字符串。
        n = len(s)
        sa = list(range(n))
        sa.sort(key=lambda x: s[x:])
        return sa

    def sa_doubling(self, s):    #实现了倍增算法，其时间复杂度为 O(n log n)，适用于中等规模字符串。
        n = len(s)
        sa = list(range(n))
        rnk = s
        t = [0] * n
        k = 1
        while k < n:
            sa.sort(key=lambda x: (rnk[x], rnk[x + k])
            if x + k < n else (rnk[x], -1))
            t[sa[0]] = 0
            for i in range(1, n):
                t[sa[i]] = t[sa[i - 1]]
                if sa[i - 1] + k < n:
                    x = (rnk[sa[i - 1]], rnk[sa[i - 1] + k])
                else:
                    x = (rnk[sa[i - 1]], -1)
                if sa[i] + k < n:
                    y = (rnk[sa[i]], rnk[sa[i] + k])
                else:
                    y = (rnk[sa[i]], -1)
                if x < y:
                    t[sa[i]] += 1
            k *= 2
            t, rnk = rnk, t
        return sa

    def sa_is(self, s, upper):
        #实现了 SA-IS 算法，其时间复杂度为 O(n)，适用于大规模字符串。
        #SA-IS 算法基于桶排思想，通过对字符串进行分类和排序，最终得到后缀数组。
        #代码中使用了类似于桶排的技巧，通过计算各种类型的后缀数量和前缀相同的子串，将问题转化为子问题，然后递归求解。
        n = len(s)
        if n == 0:
            return []
        if n == 1:
            return [0]
        if n == 2:
            if s[0] < s[1]:
                return [0, 1]
            else:
                return [1, 0]
        if n < 10:
            return self.sa_naive(s)
        if n < 50:
            return self.sa_doubling(s)
        ls = [0] * n
        for i in range(n - 2, -1, -1):
            ls[i] = ls[i + 1] if s[i] == s[i + 1] else s[i] < s[i + 1]
        sum_l = [0] * (upper + 1)
        sum_s = [0] * (upper + 1)
        for i in range(n):
            if ls[i]:
                sum_l[s[i] + 1] += 1
            else:
                sum_s[s[i]] += 1
        for i in range(upper):
            sum_s[i] += sum_l[i]
            if i < upper:
                sum_l[i + 1] += sum_s[i]
        lms_map = [-1] * (n + 1)
        m = 0
        for i in range(1, n):
            if not ls[i - 1] and ls[i]:
                lms_map[i] = m
                m += 1
        lms = []
        for i in range(1, n):
            if not ls[i - 1] and ls[i]:
                lms.append(i)
        sa = [-1] * n
        buf = sum_s.copy()
        for d in lms:
            if d == n:
                continue
            sa[buf[s[d]]] = d
            buf[s[d]] += 1
        buf = sum_l.copy()
        sa[buf[s[n - 1]]] = n - 1
        buf[s[n - 1]] += 1
        for i in range(n):
            v = sa[i]
            if v >= 1 and not ls[v - 1]:
                sa[buf[s[v - 1]]] = v - 1
                buf[s[v - 1]] += 1
        buf = sum_l.copy()
        for i in range(n - 1, -1, -1):
            v = sa[i]
            if v >= 1 and ls[v - 1]:
                buf[s[v - 1] + 1] -= 1
                sa[buf[s[v - 1] + 1]] = v - 1
        if m:
            sorted_lms = []
            for v in sa:
                if lms_map[v] != -1:
                    sorted_lms.append(v)
            rec_s = [0] * m
            rec_upper = 0
            rec_s[lms_map[sorted_lms[0]]] = 0
            for i in range(1, m):
                l = sorted_lms[i - 1]
                r = sorted_lms[i]
                end_l = lms[lms_map[l] + 1] if lms_map[l] + 1 < m else n
                end_r = lms[lms_map[r] + 1] if lms_map[r] + 1 < m else n
                same = True
                if end_l - l != end_r - r:
                    same = False
                else:
                    while l < end_l:
                        if s[l] != s[r]:
                            break
                        l += 1
                        r += 1
                    if l == n or s[l] != s[r]:
                        same = False
                if not same:
                    rec_upper += 1
                rec_s[lms_map[sorted_lms[i]]] = rec_upper
            rec_sa = self.sa_is(rec_s, rec_upper)
            for i in range(m):
                sorted_lms[i] = lms[rec_sa[i]]
            sa = [-1] * n
            buf = sum_s.copy()
            for d in sorted_lms:
                if d == n:
                    continue
                sa[buf[s[d]]] = d
                buf[s[d]] += 1
            buf = sum_l.copy()
            sa[buf[s[n - 1]]] = n - 1
            buf[s[n - 1]] += 1
            for i in range(n):
                v = sa[i]
                if v >= 1 and not ls[v - 1]:
                    sa[buf[s[v - 1]]] = v - 1
                    buf[s[v - 1]] += 1
            buf = sum_l.copy()
            for i in range(n - 1, -1, -1):
                v = sa[i]
                if v >= 1 and ls[v - 1]:
                    buf[s[v - 1] + 1] -= 1
                    sa[buf[s[v - 1] + 1]] = v - 1
        return sa
    def suffix_array(self, s, upper=255):
        if type(s) is str:
            s = [ord(c) for c in s]
        return self.sa_is(s, upper)
    def _count_sort(self, ls):
        c = defaultdict(list)
        for i, v in enumerate(ls):
            c[v].append(i)
        ans = []
        for v in sorted(list(c.keys())):
            for k in c[v]:
                ans.append((k, v))
        return ans
    def _rk(self, sa):
        rk = [0 for _ in sa]
        for i in range(len(sa)):
            rk[sa[i]] = i
        return rk

    def _height(self, s):
        sa, rk = self.sa, self.rk
        ht = [0] * len(sa)
        k = 0
        for sai in range(0, len(s)):
            if k:
                k -= 1
            while True:
                ai, bi = sai + k, sa[rk[sai] - 1] + k
                if not (0 <= ai < len(s) and 0 <= bi < len(s)):
                    break
                if max(ai, bi) >= len(s):
                    break
                elif s[ai] == s[bi]:
                    k += 1
                else:
                    break
            ht[rk[sai]] = k
        return ht
    def __init__(self, s):             #下方的i都从1开始，字典序
        if type(s) is str:             #表示排名第i小的后缀在原字符串中的起始位置为sa[i]
            self.sa = self.suffix_array(s)
        else:
            self.sa = self.suffix_array(s[:])
        self.rk = self._rk(self.sa)    #字符串中的每个位置i，其对应的后缀在后缀数组中的排名为rk[i]，排名从0开始
        self.height = self._height(s)  #第i小的后缀与它前一名的后缀的最长公共前缀，其他地方也可能交lcp
        self.height[0]=0               #在字符串为单一字符构成时(长度为1也算),heigh[0]会出错成1，按照定义应该为0


n=ii()

s=input()

t=input()

sa=SuffixArray(s+s+"$"+t+t)

res=cnt=0

for i in range(4*n,-1,-1):
    if 0<=sa.sa[i]<=n-1:
        res+=cnt
    elif 2*n+1<=sa.sa[i]<=3*n:
        cnt+=1

print(res)