n=int(input())
count=0

for i in range(n):
    s=input()
    if s=="For":
        count=count+1
    print(s,count)

print(count)

if count>n/2:
    print("Yes")
else:
    print("No")