import itertools
def isok(n,c,a):
    for j in range(n):
        cnt=[0,0,0]
        for i in range(n):
            if a[i][j]!=".":
                if cnt==0 and a[i][j]!=c[j]:
                    return False
                cnt[ord(a[i][j])-ord('A')]+=1
        if cnt!=[1,1,1]:
            return False
    return True

def make(n,s):#sは1番左の文字
    res=[]
    tmp=["A","B","C"]
    tmp.remove(s)
    for i in itertools.combinations(range(n),3):
        g=["."]*n
        g[i[0]]=s
        g[i[1]]=tmp[0]
        g[i[2]]=tmp[1]
        res.append(g)
        g=list(g)
        g[i[1]]=tmp[1]
        g[i[2]]=tmp[0]
        res.append(g)
    return res
        
n=int(input())
r=input()
c=input()

if n==3:
    for i1 in make(n,r[0]):
        for i2 in make(n,r[1]):
            for i3 in make(n,r[2]):
                if isok(n,c,[i1,i2,i3]):
                    print("Yes")
                    print(''.join(i1))
                    print(''.join(i2))
                    print(''.join(i3))
                    exit()
elif n==4:
    for i1 in make(n,r[0]):
        for i2 in make(n,r[1]):
            for i3 in make(n,r[2]):
                for i4 in make(n,r[3]):
                    if isok(n,c,[i1,i2,i3,i4]):
                        print("Yes")
                        print(''.join(i1))
                        print(''.join(i2))
                        print(''.join(i3))
                        print(''.join(i4))
                        exit()
else:
    for i1 in make(n,r[0]):
        for i2 in make(n,r[1]):
            for i3 in make(n,r[2]):
                for i4 in make(n,r[3]):
                    for i5 in make(n,r[4]):
                        if isok(n,c,[i1,i2,i3,i4,i5]):
                            print("Yes")
                            print(''.join(i1))
                            print(''.join(i2))
                            print(''.join(i3))
                            print(''.join(i4))
                            print(''.join(i5))
                            exit()
print("No")