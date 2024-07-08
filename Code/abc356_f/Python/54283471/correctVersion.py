class SegmentTree:
    def __init__(self, a):
        self.padding = 0
        self.n = len(a)
        self.N = 2 ** (self.n-1).bit_length()
        self.seg_data = [self.padding]*(self.N-1) + a + [self.padding]*(self.N-self.n)
        for i in range(2*self.N-2, 0, -2):
            self.seg_data[(i-1)//2] = self.seg_data[i] + self.seg_data[i-1]
    
    def __len__(self):
        return self.n
    
    def __getitem__(self, i):
        return self.seg_data[self.N-1+i]
    
    def __setitem__(self, i, x):
        idx = self.N - 1 + i
        self.seg_data[idx] = x
        while idx:
            idx = (idx-1) // 2
            self.seg_data[idx] = self.seg_data[2*idx+1] + self.seg_data[2*idx+2]

    def query(self, i, j):
        # [i, j)
        if i == j:
            return 0
        else:
            idx1 = self.N - 1 + i
            idx2 = self.N - 2 + j # 閉区間にする
            result = self.padding
            while idx1 < idx2 + 1:
                if idx1&1 == 0: # idx1が偶数
                    result = result + self.seg_data[idx1]
                if idx2&1 == 1: # idx2が奇数
                    result = result + self.seg_data[idx2]
                    idx2 -= 1
                idx1 //= 2
                idx2 = (idx2 - 1)//2
            return result
    
    def kth_left_idx(self, fr, k):
        if self.query(0, fr+1) < k:
            return -1
        remain = k
        now = fr + self.N - 1
        while self.seg_data[now] < remain:
            if now % 2:
                remain -= self.seg_data[now]
                now -= 1
            else:
                now = (now - 1) // 2
        
        while now < self.N - 1:
            nl = 2*now + 1
            nr = nl + 1
            if self.seg_data[nr] < remain:
                remain -= self.seg_data[nr]
                now = nl
            else:
                now = nr
        
        return now - (self.N - 1)

    def kth_right_idx(self, fr, k):
        if self.query(fr, self.n) < k:
            return -1
        remain = k
        now = fr + self.N - 1
        while self.seg_data[now] < remain:
            if now % 2 == 0:
                remain -= self.seg_data[now]
                now += 1
            else:
                now //= 2
        
        while now < self.N - 1:
            nl = 2*now + 1
            nr = nl + 1
            if self.seg_data[nl] < remain:
                remain -= self.seg_data[nl]
                now = nr
            else:
                now = nl
        
        return now - (self.N - 1)


def compress(data):
    s = sorted(set(data))
    idx = dict(zip(s, range(len(s))))
    return s, idx


Q, K = map(int, input().split())
INF = K+10
query = [list(map(int, input().split())) for _ in range(Q)]
X = [x for _,x in query]
X += [-INF, max(X)+INF]
X, x2id = compress(X)
N = len(X)

In = SegmentTree([0]*N)
Left = SegmentTree([0]*N)
In[0] = In[N-1] = Left[0] = Left[N-1] = 1

for t, x in query:
    i = x2id[x]
    if t == 1:
        if In[i]:
            In[i] = 0
            if Left[i]:
                Left[i] = 0
                ri = In.kth_right_idx(i, 1)
                Left[ri] = 1
            else:
                li = In.kth_left_idx(i, 1)
                ri = In.kth_right_idx(i, 1)
                if X[ri] - X[li] > K:
                    Left[ri] = 1
        else:
            li = In.kth_left_idx(i, 1)
            ri = In.kth_right_idx(i, 1)
            In[i] = 1
            if Left[ri]:
                marge_left = x - X[li] <= K
                marge_right = X[ri] - x <= K
                if marge_left:
                    if marge_right:
                        Left[ri] = 0
                else:
                    Left[i] = 1
                    if marge_right:
                        Left[ri] = 0
    else:
        li = Left.kth_left_idx(i, 1)
        ri = Left.kth_right_idx(i+1, 1)
        ans = In.query(li, ri) - int(li==0)
        print(ans)