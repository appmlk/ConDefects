N = int(input())
s = "RGB"
ans = [""]*(N-1)
cnts = [0]*3
if N%3==2:
    print("No")
    exit()
cnt = N*(N-1)//6
for i in range(N-1):
    for c in range(3):
        if cnts[c] + (N-1-i) <= cnt:
            ans[i] = s[c]*(N-1-i)
            cnts[c] += N-1-i
            break
    else:
        print("No")
        exit()
print("Yes")
print(*ans, sep='\n')