class SegTree:
    def __init__(self, init_list, func=lambda x,y: x+y, ide_ele=0):
        self.n = len(init_list)
        self.length = 1<<(self.n-1).bit_length()
        self.node_list = [ide_ele]*(2*self.length)
        self.func = func
        self.ide_ele = ide_ele
        for i in range(self.n):
            self.node_list[i+self.length] = init_list[i]
        for i in range(self.length-1, 0, -1):
            self.node_list[i] = self.func(self.node_list[2*i], self.node_list[2*i+1])
    
    def add(self, index, x):
        if not 0 <= index < self.n: raise Exception("segtree index out of range")
        index += self.length
        self.node_list[index] = self.func(self.node_list[index], x)
        while index > 1:
            self.node_list[index>>1] = self.func(self.node_list[index], self.node_list[index^1])
            index >>= 1
    
    def update(self, index, x):
        if not 0 <= index < self.n: raise Exception("segtree index out of range")
        index += self.length
        self.node_list[index] = x
        while index > 1:
            self.node_list[index>>1] = self.func(self.node_list[index], self.node_list[index^1])
            index >>= 1
    
    def query(self, l, r):
        if not (0 <= l <= self.n and 0 <= r <= self.n): raise Exception("segtree index out of range")
        ans = self.ide_ele
        l += self.length
        r += self.length
        while l < r:
            if l & 1:
                ans = self.func(ans, self.node_list[l])
                l += 1
            if r & 1:
                ans = self.func(ans, self.node_list[r-1])
            l >>= 1
            r >>= 1
        return ans
    
    def __getitem__(self, index):
        if type(index) != int: raise Exception("segtree indices must be integers")
        if not 0 <= index < self.n: raise Exception("segtree index out of range")
        return self.node_list[index+self.length]
    
    def __setitem__(self, index, value):
        if type(index) != int: raise Exception("segtree indices must be integers")
        if not 0 <= index < self.n: raise Exception("segtree index out of range")
        self.update(index, value)
    
    def __str__(self):
        return str(self.node_list[self.length:self.length+self.n])

def solve():
    n, k = map(int, input().split())
    p_list = list(map(int, input().split()))
    a_list = list(map(int, input().split()))
    tree = [set() for _ in range(n)]
    for i in range(n-1):
        p = p_list[i]
        tree[i+1].add(p-1)
        tree[p-1].add(i+1)
    
    todo = [0]
    ecount_list = [-2]*n
    num_list = [set() for _ in range(n)]
    while todo:
        t = todo[-1]
        if ecount_list[t] == -2:
            for i in tree[t]:
                if ecount_list[i] == -2:
                    todo.append(i)
            ecount_list[t] = -1
        elif ecount_list[t] == -1:
            if a_list[t] == -1:
                count = 1
            else:
                count = 0
                if a_list[t] <= k: num_list[t].add(a_list[t])
            
            for i in tree[t]:
                if ecount_list[i] > 0:
                    count += ecount_list[i]
                num_list[t] |= num_list[i]
            ecount_list[t] = count
            
            todo.pop()
    count = 0
    #print(ecount_list)
    #print(num_list)
    for i in range(n):
        if ecount_list[i] == 0 and len(num_list[i]) == k and k not in num_list[i]:
            print("Alice")
            break
        elif ecount_list[i] == 1 and len(num_list[i]) == k-1 and k not in num_list[i]:
            print("Alice")
            break
    else:
        print("Bob")
    

t = int(input())

for _ in range(t):
    solve()