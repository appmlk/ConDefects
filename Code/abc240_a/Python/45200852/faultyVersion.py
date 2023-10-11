a,b=map(int,input().split())
print(['YNeos'[a!=1::2],'YNeos'[b-1!=a::2]][b<10])