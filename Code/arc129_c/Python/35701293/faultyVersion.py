N = int(input())
ans = []
now = 0
count = 0
while N>0:
    for i in range(2000):
        if (i+1)*(i+2)//2 > N:
            N -= i*(i+1)//2
            break
    ans.append("7"*i)
    now += i
    delim = pow(10,5*now,7)
    if delim==0:
        delim = 7
    now += 1
    ans.append(str(delim))
    count += 1
    if count==8:
        a

print("".join(ans)[:-1])
