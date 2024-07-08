def find_unique_char_position(S):
    # 最初の3文字をチェックして異なる文字を特定
    if S[0] == S[1]:
        common_char = S[0]
    elif S[2] == S[1]:
      common_char = S[1]
    else :
      return 2

    # 異なる1文字の位置を特定
    for i, char in enumerate(S):
        if char != common_char:
            return i + 1  # 1-based index

# 入力
S = input().strip()

# 異なる文字の位置を特定して出力
print(find_unique_char_position(S))