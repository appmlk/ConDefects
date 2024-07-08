import sys
input = sys.stdin.readline

from functools import lru_cache
@lru_cache(maxsize=None)
def grundy(N,L):
    if N<L:
        return 0
    
    SET={grundy(N-L,L)}

    x=N-L
    
    for i in range(N-L):
        SET.add(grundy(i,L)^grundy(x-i,L))

    for i in range(10**9):
        if i in SET:
            pass
        else:
            return i

N,L,R=map(int,input().split())
flag=0

if N%2==0:
    for i in range(L,R+1):
        if i%2==0:
            print("First",flush=True)
            x=(1+N)//2+1-i//2
            print(x,i,flush=True)
            flag=1
            break

    while flag==1:
        a,b=map(int,input().split())
        if a==b==0:
            break
        if a==b==-1:
            break
        r=[a,a+b-1]
        gyaku=1+N-(a+b-1)

        print(gyaku,b,flush=True)

else:
    for i in range(L,R+1):
        if i%2==1:
            print("First",flush=True)
            x=(1+N)//2-i//2
            print(x,i,flush=True)
            flag=1
            break

    while flag==1:
        a,b=map(int,input().split())
        if a==b==0:
            break
        if a==b==-1:
            break
        r=[a,a+b-1]
        gyaku=1+N-(a+b-1)

        print(gyaku,b,flush=True)


A=[0]*N

if flag==0:
    assert (L==R)
    gr=grundy(N,L)

    if gr!=0:
        print("First",flush=True)

        while True:
            X=[]
            S=[]
            now=0
            for i in range(N):
                if A[i]==0:
                    if now==0:
                        S.append(i)
                    now+=1

                else:
                    if now>0:
                        X.append(now)
                        now=0

            if now>0:
                X.append(now)

            XOR=0
            for x in X:
                XOR^=grundy(x,L)

            for i in range(len(X)):
                k=XOR^grundy(X[i],L)

                if grundy(X[i],L)>k:
                    ind=i
                    target_g=k

                    rest=-1

                    for i in range(X[ind]):
                        if grundy(X[ind]-i-L,L)^grundy(i,L)==target_g:
                            rest=i
                            break

                    if rest!=-1:
                        break

            print(S[ind]+rest+1,L,flush=True)
            for i in range(S[ind]+rest,S[ind]+rest+L):
                A[i]=1

            a,b=map(int,input().split())
            if a==b==0:
                break
            if a==b==-1:
                break

            for i in range(a-1,a+b-1):
                A[i]=1
     
    else:
        print("Second",flush=True)

        while True:
            a,b=map(int,input().split())
            if a==b==0:
                break
            if a==b==-1:
                break

            for i in range(a-1,a+b-1):
                A[i]=1
                
            X=[]
            S=[]
            now=0
            for i in range(N):
                if A[i]==0:
                    if now==0:
                        S.append(i)
                    now+=1

                else:
                    if now>0:
                        X.append(now)
                        now=0

            if now>0:
                X.append(now)

            XOR=0
            for x in X:
                XOR^=grundy(x,L)

            for i in range(len(X)):
                k=XOR^grundy(X[i],L)

                if grundy(X[i],L)>k:
                    ind=i
                    target_g=k

                    rest=-1

                    for i in range(X[ind]):
                        if grundy(X[ind]-i-L,L)^grundy(i,L)==target_g:
                            rest=i
                            break

                    if rest!=-1:
                        break

            print(S[ind]+rest+1,L,flush=True)
            for i in range(S[ind]+rest,S[ind]+rest+L):
                A[i]=1
    
