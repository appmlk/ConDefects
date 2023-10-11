n=int(input())
x=list(input().split())
eng=["and","not","that","the","you"]

for i in range(n):
    print(i)
    print(x[i])
    if x[i] in eng:
        print("Yes")
        break
    elif i==n-1:
        print("No")