x,y,n = map(int,input().split())
yn = n//3
xn = n - yn*3
print(min(xn*x +  yn*y , n*x))