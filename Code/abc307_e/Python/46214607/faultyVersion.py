N,M=map(int,input().split())
mod=998244353

answer=(-1)**(N%2)*(M-1)+pow(M-1,N,mod)
print(answer)