class SegmentTree:
    def __init__(self, a):
        self.padding = float('inf')        
        self.n = len(a)
        self.N = 2 ** (self.n-1).bit_length()
        self.seg_data = [self.padding]*(self.N-1) + a + [self.padding]*(self.N-self.n)
        for i in range(2*self.N-2, 0, -2):
            self.seg_data[(i-1)//2] = min(self.seg_data[i], self.seg_data[i-1])
    
    def __len__(self):
        return self.n
    
    def __getitem__(self, i):
        return self.seg_data[self.N-1+i]
    
    def update(self, i, x):
        idx = self.N - 1 + i
        self.seg_data[idx] = x
        while idx:
            idx = (idx-1) // 2
            self.seg_data[idx] = min(self.seg_data[2*idx+1], self.seg_data[2*idx+2])
    
    def query(self, i, j):
        # [i, j)
        if i == j:
            return None
        else:
            idx1 = self.N - 1 + i
            idx2 = self.N - 2 + j # 閉区間にする
            result = self.padding
            while idx1 < idx2 + 1:
                if idx1&1 == 0: # idx1が偶数
                    result = min(result, self.seg_data[idx1])
                if idx2&1 == 1: # idx2が奇数
                    result = min(result, self.seg_data[idx2])
                    idx2 -= 1
                idx1 //= 2
                idx2 = (idx2 - 1)//2
            return result


N, K = map(int, input().split())
P = list(map(int, input().split()))

if K == 0:
    print(*P)
    quit()


def solve1(data, k):
    if k == 0:
        return data
    
    ans = []
    n = len(data)
    pos = dict(zip(data, range(n)))
    st = SegmentTree(data)
    left = 0
    while k and left<n:
        m = st.query(left, min(n, left+k+1))
        idx = pos[m]
        k -= idx - left
        left = idx + 1
        if k:
            ans.append(m)
        else:
            ans += data[idx:]
    return ans


def solve2(data1, data2, k):
    res1 = []
    res2 = solve1(data2, k)
    if len(data1)==0 or data1[0] > res2[0]:
        return res2
    
    if data1:
        res1 = [data1[0]]
        n = len(data1)
        pos = dict(zip(data1, range(n)))
        st = SegmentTree(data1)
        left = 1
        while left < n:
            m = st.query(left, n)
            if m > res2[0]:
                break
            res1.append(m)
            left = pos[m] + 1
    return res1 + res2


ans1 = solve1(P.copy(), K)

i = N - 1
idx = m = N+1
for _ in range(K):
    if P[i] < m:
        idx = i
        m = P[i]
    i -= 1

data1 = P[idx:]
data2 = P[:idx]
use = N - idx
remain = K - use
ans2 = solve2(data1, data2, remain)

ans = min(ans1, ans2)
print(*ans)