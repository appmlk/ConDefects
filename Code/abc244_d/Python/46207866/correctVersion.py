S1,S2,S3 = input().split()
T1,T2,T3 = input().split()
cnt = 0
if S1 != T1:
    cnt += 1
if S2 != T2:
    cnt += 1
if S3 != T3:
    cnt += 1
print("Yes" if cnt==0 or cnt==3 else "No")
