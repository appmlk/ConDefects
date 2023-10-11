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

n,k = list(map(int, input().split()))
s = input()
# s = "RLRRRLLLLLLLLRRRLLRLRRR"*200
# k = 1
# n = len(s)

def main(n,k,s):
# if 1:
    if s.count("R")<s.count("L"):
        s = "".join([("L" if c=="R" else "R") for c in s])
        s = s[::-1]
    cum = [0]
    vs = []
    c = 0
    ind = -1
    v0 = []
    v1 = []
    for i in range(n):
        if s[i]=="L":
            c -= 1
            vs.append(-1)
            v0.append((c,i))
        else:
            c += 1
            vs.append(1)
            v1.append((c,i))
        cum.append(c)
        if c>0 and ind==-1:
            ind = i
    v0.sort()
    v1.sort()
    v0orig = v0[:]
    v1orig = v1[:]
    val0 = val1 = 0
    res0 = res1 = 0
    # print(len(v0), len(v1))
    for i in range(k):
        val = c*(k-i)
        while v1 and v1[-1][0]>val:
            val1 += 1
            v1.pop()
        while v0 and v0[-1][0]>=-c*i:
            val0 += 1
            v0.pop()
        res1 += val1
        res0 += val0
    ans = res0 + res1
#     print(ans)
    # print(ans)
    if c>0:
        v0 = v0orig[:]
        v1 = v1orig[:]
        tmp0 = tmp1 = 0
        for v,ii in v1:
            if c==0:
                val = k if v>c*k else 0
            else:
                val = max(0, min((v-1)//c, k))
            if val==k and ii<ind:
                val -= 1
            tmp1 += val
    #         for j in range(k):
    #             val = v + j*c
    #             if val>c*k and (j>0 or ii>=ind):
    #                 tmp1 += 1
        for v,ii in v0:
            if c==0:
                val = k if v<0 else 0
            else:
                val = max(0, min((-v+c-1)//c, k))
            if val>0 and ii<ind:
                val -= 1
            tmp0 += val
    #         for j in range(k):
    #             val = v + j*c
    #             if val<0 and (j>0 or ii>=ind):
    #                 tmp0 += 1
        ans += tmp0 + tmp1
    # tmp = []
    # cc = 0
    # for i in range(n):
    #     if vs[i]==1:
    #         cc += 1
    #     else:
    #         cc -= 1
    #     if (vs[i]==-1 and cc<0) or (vs[i]==1 and cc>c*k):
    #         tmp.append(1)
    #     else:
    #         tmp.append(0)
    # if ind>=0:
    #     ans += sum(tmp[ind:])
    #     ans += sum(tmp)*(k-1)
    M = 998244353
    ans %= M
    return (ans%M)
def main2(n,k,s):
    return main(n*k, 1, s*k)
ans = main(n,k,s)
print(ans)