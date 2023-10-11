class Input_kyopro:
    def II(self): return int(input())
    def MI(self): return map( int,input().split())
    def MS(self): return map(str,input().split())
    def LMI(self): return list(self.MI())
    def LMS(self): return list(self.MS())
    def LLI(self,N): return [self.LMI() for _ in range(N)]
    def LLS(self,N): return [self.LMS() for _ in range(N)]
    def LS(self,N): return [input() for _ in range(N)]
    def LSL(self,N): return [list(input()) for _ in range(N)]
    def LI(self,N): return [self.II() for _ in range(N)]
I=Input_kyopro()
#入力
N,L,R=I.MI()
A=I.LMI()
ruiseki_l=[0]*(N+1)
s=sum(A)
for i in range(N):
    ruiseki_l[i+1]=ruiseki_l[i]+L-A[i]
for i in range(N):
    ruiseki_l[i+1]=min(ruiseki_l[i+1],ruiseki_l[i])
ruiseki_r=[0]*(N+1)
for i in range(N):
    ruiseki_r[i+1]=ruiseki_r[i]+R-A[-i-1]
for i in range(N):
    ruiseki_r[i+1]=min(ruiseki_r[i+1],ruiseki_r[i])
ans=float('inf')
for i in range(N):
    ans=min(ans,ruiseki_l[i]+ruiseki_r[-i-1]+s)
print(ans)

    