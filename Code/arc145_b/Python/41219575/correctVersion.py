N,A,B=map(int,input().split())

if A<=B:
    print(max(N-A+1,0))
    exit()

answer=B*(N//A-1)
answer+=min(N%A+1,B)

print(max(answer,0))