N = list(str(input()))
likeNum = True

for i in range(len(N)-1):
    if len(N) == 1:
        break
    elif int(N[i]) < int(N[i+1]):
        likeNum = False
        print("No")
        break

if likeNum:
    print("Yes")
