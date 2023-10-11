N, M = map(int, input().split())
dict = {}
for i in range(M):
    a, b = map(int, input().split())
    dict.setdefault(a, []).append(b)
    dict.setdefault(b, []).append(a)

# キーで辞書をソート
sortDict = sorted(dict.items(), key=lambda x: x[0])

# キーの値（リスト）をソートして出力
for key, value in sortDict:
    print(len(value), end=" ")
    print(*sorted(value))
