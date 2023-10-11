(h,w,C,q),*z=[[*map(int,t.split())]for t in open(0)]
a=[0]*C;s=u=set()
for t,n,c in z[::-1]:
 if t==1 and n not in s:a[c-1]+=w;s|={n};h-=1
 elif t==2 and n not in u:a[c-1]+=h;u|={n};w-=1
print(*a)