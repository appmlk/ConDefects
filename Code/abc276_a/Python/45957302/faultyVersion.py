a=input()
b=len(a)
for i in range(b-1,0,-1):
    if a[i]=="a":
        print(i+1)
        quit()
print(-1)