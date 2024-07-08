h,w=list(map(int,input().split()))
a=[input() for _ in range(h)]
n=int(input())
rce=[list(map(int,input().split())) for _ in range(n)]
rc={(rce[i][0]-1,rce[i][1]-1):rce[i][2] for i in range(n)}

for i in range(h):
    for j in range(w):
        if a[i][j]=="S":
            s=(i,j)
        if a[i][j]=="T":
            t=(i,j)
if not(s in rc):
    exit(print("No"))
ans=set()
aans=set()
aaans=set()
ans.add(s)
aans.add(s)
def sk(b,n):
    global ans,aans
    do=((1,0),(-1,0),(0,1),(0,-1))
    si=set()
    si.add(b)
    ti=set()
    ti.add(b)
    ki=set()
    x=0
    while (not(len(ti)==0))and(x!=n):
        x+=1
        for i in ti:
            for j in do:
                k=(i[0]+j[0],i[1]+j[1])
                if (0>k[0])or(h<=k[0])or(0>k[1])or(w<=k[1]):#サイズを変えるならここ
                    continue
                if (a[k[0]][k[1]]=="#"):#障害物がいらないならここを消す
                    continue
                if not(k in si):
                    ki.add(k)
                    si.add(k)
                if (k in rc.keys())and(not(k in ans)):
                    ans.add(k)
                    aaans.add(k)
                if k==t:
                    exit(print("Yes"))
        ti=ki
        ki=set()
    return -1


while aans:
    for i in aans:
        sk(i,rc[i])
    aans=aaans
    aaans=set()
print("No")



