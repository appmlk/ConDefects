n = int(input())
for i in range(n,920):
    c = str(i)
    if int(c[0])*int(c[1])==int(c[2]):
        print(i)
        exit()