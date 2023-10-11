r,c=map(int,input().split())
print(("white","blue")[max(abs(r-8),abs(c-8))%2])