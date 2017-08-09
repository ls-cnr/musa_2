function b = seloffer( x )

    %% SELOFFER
    % select best offer package indexes from matrix x

    k = size(x, 1);
    n = size(x, 3);

    % more-is-better approach
    [~, b] = max(sum(x - repmat(min(x),k,1) ./ ...
    repmat(sum(min(x),3) - sum(max(x),3), k, 1, n), 2));

    b = squeeze(b);

    if length(b) == 1
        b = ones(n, 1);
    end

end
