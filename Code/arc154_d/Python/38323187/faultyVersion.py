import sys, random
input = lambda : sys.stdin.readline().rstrip()


write = lambda x: sys.stdout.write(x+"\n"); writef = lambda x: print("{:.12f}".format(x))
debug = lambda x: sys.stderr.write(x+"\n")
YES="Yes"; NO="No"; pans = lambda v: print(YES if v else NO); INF=10**18
LI = lambda : list(map(int, input().split())); II=lambda : int(input()); SI=lambda : [ord(c)-ord("a") for c in input()]
def debug(_l_):
    for s in _l_.split():
        print(f"{s}={eval(s)}", end=" ")
    print()
def dlist(*l, fill=0):
    if len(l)==1:
        return [fill]*l[0]
    ll = l[1:]
    return [dlist(*ll, fill=fill) for _ in range(l[0])]

# 標準出力による質問 interactive
TEST = 0
import sys
def _q(i,j,k):
    print("?", i+1, j+1, k+1)
    sys.stdout.flush()
    return input()=="Yes"
def answer(v):
    print(f"! {v}")
    sys.stdout.flush()
    
n = int(input())
if TEST:
    import random
    _a = list(range(1,n+1))
    random.shuffle(_a)
    def _q(i,j,k):
        return _a[i]+_a[j]>_a[k]
ind = 0
for i in range(1,n):
    res = _q(ind,ind,i)
    if res:
        ind = i
if TEST:
    assert _a[ind]==1
def sub(index):
    # index に含まれるインデックスの値をソートして返す
    if len(index)<=1:
        return index
    m = len(index)//2
    l = sub(index[:m])
    r = sub(index[m:])
    assert len(l)+len(r)==len(index)
    li = ri = 0
    ans = []
    for _ in range(len(index)):
        if li<len(l) and ri<len(r):
            res = _q(l[li], ind, r[ri])
            if res:
                ans.append(r[ri])
                ri += 1
            else:
                ans.append(l[li])
                li += 1
        else:
            break
    ans.extend(l[li:])
    ans.extend(r[ri:])
    assert len(ans)==len(index)
    return ans
index = list(range(n))
index.remove(ind)
res = sub(index)
ans = [0]*n
ans[ind] = 1
for i in range(n-1):
    ans[res[i]] = i+2
write(" ".join(map(str, ans)))
sys.stdout.flush()
if TEST:
    assert _a==ans