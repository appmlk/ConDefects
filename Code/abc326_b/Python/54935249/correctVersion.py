N = int(input())

for i in range(N,920):
    s = str(i)
    if int(s[0]) * int(s[1]) == int(s[2]):
        print(i)
        exit()


