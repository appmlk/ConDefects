T=int(input())
for i in range(T):
    Nx,A,B=map(int,input().split())
    Ny=Nx
    Ny2=Ny/2
    Nx-=A
    if(Ny2<A):
        Ny-=(A-Ny2)
    Ny-=Ny2
    if(Nx<0 or Ny*Nx<B):
        print("No")
    else:
        print("Yes")
