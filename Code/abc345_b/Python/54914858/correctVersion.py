X = int(input())
if X % 10 == 0:
    result = int(X//10)
else:
    result = int(X//10 + 1)
print(result)