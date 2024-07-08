def generate_substrings(s):
    substrings = []
    n = len(s)
    for length in range(1, n + 1):  # 長さ1からnまでの部分文字列を生成する
        for start in range(n - length + 1):
            substr = s[start:start + length]
            substrings.append(substr)
    return substrings
s = input()
print(len(generate_substrings(s)))