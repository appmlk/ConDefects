import bisect
H,W,rs,cs=map(int,input().split())
N=int(input())

r_dic={}
c_dic={}

for _ in range(N):
    r,c=map(int,input().split())
    if r not in r_dic:
        r_dic[r]=set()
        r_dic[r].add(c)
    else:
        r_dic[r].add(c)
    if c not in c_dic:
        c_dic[c]=set()
        c_dic[c].add(r)
    else:
        c_dic[c].add(r)

for r in r_dic:
    r_dic[r]=[0]+sorted(list(r_dic[r]))+[W+1]
for c in c_dic:
    c_dic[c]=[0]+sorted(list(c_dic[c]))+[H+1]

Q=int(input())

def upper_bound(array,x):
    index=bisect.bisect_right(array,x-1)
    return array[index]

def lower_bound(array,x):
    index=bisect.bisect_right(array,x)-1
    return array[index]

x=rs
y=cs
for _ in range(Q):
    d,l=input().split()
    l=int(l)
    if d=='L':
        if x in r_dic:
            y=max(lower_bound(r_dic[x],y)+1,y-l)
        else:
            y=max(1,y-l)
    elif d=='R':
        if x in r_dic:
            y=min(upper_bound(r_dic[x],y)-1,y+l)
        else:
            y=min(W,y+l)
    elif d=='U':
        if y in c_dic:
            x=max(lower_bound(c_dic[y],x)+1,x-l)
        else:
            x=max(1,x-l)
    else:
        if y in c_dic:
            x=min(upper_bound(c_dic[y],x)-1,x+l)
        else:
            x=min(H,y+l)
    print(x,y)