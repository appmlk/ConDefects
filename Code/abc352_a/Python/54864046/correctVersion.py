N, X, Y, Z = map(int, input().split())

start = min(X, Y)
end = max(X, Y)

result = "No"
if start <= Z and end >= Z:
    result = "Yes"

print(result)
