r,c=map(int,input().split())
print(("white","black")[max(abs(r-8),abs(c-8))%2])